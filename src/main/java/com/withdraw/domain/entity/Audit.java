package com.withdraw.domain.entity;

import com.withdraw.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Audit {
    @Column(length = 100, nullable = false)
    String createdAt;
    @Column(length = 100, nullable = false)
    String lastUpdate;

    @PrePersist
    public void prePersist() {
        String now = LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateUtil.DATE_TIME_ZONE_FORMATTER);
        createdAt = now;
        lastUpdate = now;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = ZonedDateTime.parse(createdAt).toLocalDateTime().atOffset(ZoneOffset.UTC).format(DateUtil.DATE_TIME_ZONE_FORMATTER);
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = ZonedDateTime.parse(lastUpdate).toLocalDateTime().atOffset(ZoneOffset.UTC).format(DateUtil.DATE_TIME_ZONE_FORMATTER);
    }

    public void setLastUpdateNow() {
        this.lastUpdate = LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateUtil.DATE_TIME_ZONE_FORMATTER);
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateUtil.DATE_TIME_ZONE_FORMATTER);
    }
}
