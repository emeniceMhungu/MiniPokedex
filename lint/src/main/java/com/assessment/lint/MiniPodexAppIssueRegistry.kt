

package com.assessment.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.assessment.lint.designsystem.DesignSystemDetector


class MiniPodexAppIssueRegistry : IssueRegistry() {

    override val issues = listOf(
        DesignSystemDetector.ISSUE,
        TestMethodNameDetector.FORMAT,
        TestMethodNameDetector.PREFIX,
    )

    override val api: Int = CURRENT_API

    override val minApi: Int = 12

    override val vendor: Vendor = Vendor(
        vendorName = "MiniPodexApp",
        feedbackUrl = "https://github.com/emeniceMhungu/MiniPokedex/issues",
        contact = "https://github.com/emeniceMhungu/MiniPokedex",
    )
}
