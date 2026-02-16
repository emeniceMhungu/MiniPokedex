package com.assessment.common.mapper

import com.assessment.common.domain.mapper.toInstant
import com.assessment.common.domain.mapper.toLocalDate
import com.assessment.common.domain.mapper.toLocalDateTime
import com.assessment.common.domain.mapper.toReadableString
import org.junit.Assert
import org.junit.Test
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MappersUtilExtensionsTest {

    @Test
    fun `toLocalDate from Long converts epoch day correctly`() {
        // Arrange
        val epochDay: Long = 19358 // This corresponds to 2023-01-01
        val expectedDate = LocalDate.of(2023, 1, 1)

        // Act
        val actualDate = epochDay.toLocalDate()

        // Assert
        Assert.assertEquals(expectedDate, actualDate)
    }

    @Test
    fun `toReadableString from Instant formats correctly based on system timezone`() {
        // Arrange
        val instant = Instant.parse("2023-01-01T12:00:00Z")
        // Expected format depends on the machine's timezone, so we calculate it for robustness.
        val expectedString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault())
            .format(instant)

        // Act
        val actualString = instant.toReadableString()

        // Assert
        Assert.assertEquals(expectedString, actualString)
    }

    @Test
    fun `toLocalDateTime from Instant converts correctly`() {
        // Arrange
        val instant = Instant.parse("2023-01-01T12:00:00Z")
        val expectedLocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

        // Act
        val actualLocalDateTime = instant.toLocalDateTime()

        // Assert
        Assert.assertEquals(expectedLocalDateTime, actualLocalDateTime)
    }

    @Test
    fun `toLocalDate from Instant converts correctly`() {
        // Arrange
        val instant = Instant.parse("2023-01-01T12:00:00Z")
        val expectedDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()

        // Act
        val actualDate = instant.toLocalDate()

        // Assert
        Assert.assertEquals(expectedDate, actualDate)
    }

    @Test
    fun `toInstant from Long converts epoch second correctly`() {
        // Arrange
        val epochSecond: Long = 1672574400 // This corresponds to 2023-01-01T12:00:00Z
        val expectedInstant = Instant.ofEpochSecond(epochSecond)

        // Act
        val actualInstant = epochSecond.toInstant()

        // Assert
        Assert.assertEquals(expectedInstant, actualInstant)
    }
}