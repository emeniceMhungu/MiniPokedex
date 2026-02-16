package com.assessment.lint

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.android.tools.lint.detector.api.TextFormat
import com.intellij.psi.PsiField
import com.intellij.psi.PsiParameter
import com.intellij.psi.PsiVariable
import org.jetbrains.uast.UElement
import java.util.EnumSet

/**
 * Detector to ensure Moshi @Json annotations are applied to constructor parameters (@param:Json)
 * rather than to the backing field (@field:Json). Applying to fields can break Kotlin reflection
 * adapters and cause default/empty values when JSON keys are snake_case.
 */
class MoshiJsonUseSiteDetector : Detector(), SourceCodeScanner {

    override fun applicableAnnotations(): List<String> = listOf("com.squareup.moshi.Json")

    override fun visitAnnotationUsage(
        context: JavaContext,
        element: UElement,
        annotationInfo: com.android.tools.lint.detector.api.AnnotationInfo,
        usageInfo: com.android.tools.lint.detector.api.AnnotationUsageInfo,
    ) {
        val referenced = usageInfo.referenced
        // If the annotation is applied to a field (backing property) we should warn
        // Prefer application to constructor parameter (param) instead.
        val psi = referenced as? PsiVariable ?: return

        // If it's a parameter, it's fine
        if (psi is PsiParameter) return

        // If it's a field or other variable, flag it
        if (psi is PsiField) {
            context.report(
                ISSUE,
                usageInfo.usage,
                context.getLocation(usageInfo.usage),
                "@field:Json detected — prefer using @param:Json on constructor parameters so Moshi's Kotlin adapter maps snake_case JSON keys correctly.",
            )
        }
    }

    companion object {
        @JvmField
        val ISSUE: Issue = Issue.create(
            id = "MoshiJsonUseSite",
            briefDescription = "Prefer @param:Json for Moshi annotations",
            explanation = "Annotate Moshi Json with @param:Json on constructor parameters instead of @field:Json. " +
                "Field-level annotations may be ignored by the Kotlin reflection adapter and lead to default values.",
            category = Category.CORRECTNESS,
            priority = 6,
            severity = Severity.ERROR,
            implementation = Implementation(
                MoshiJsonUseSiteDetector::class.java,
                EnumSet.of(Scope.JAVA_FILE),
            ),
        )
    }
}

