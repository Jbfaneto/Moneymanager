package com.moneymanager.moneymanager.domain.activity;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.moneymanager.moneymanager.domain.activity.type.Type;
import com.moneymanager.moneymanager.utils.InstantUtils;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class ActivityTest {
    // Given
    // When
    // Then
    @Test
    public void givenValidParams_whenBuildingNewActivity_thenReturnNewValidActivity() {

        final var aDescription = "A description";
        final var aValue = 3.55f;
        final var aDate = InstantUtils.now();
        final var aType = Type.REVENUE;

        final var anActivity = Activity.newActivity(aDate, aDescription, aValue, aType, InstantUtils.now(), InstantUtils.now());

        assertNotNull(anActivity);
        assertNotNull(anActivity.getId());
        assertTrue(anActivity.getId().length() == 36);
        assertEquals(aDescription, anActivity.getDescription());
        assertEquals(aDate, anActivity.getDate());
        assertEquals(aValue, anActivity.getValue(), 0.001);
        assertEquals(aType, anActivity.getType());
        assertNotNull(anActivity.getCreatedAt());
        assertNotNull(anActivity.getUpdatedAt());
    }
}
