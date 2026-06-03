package com.ems.model;

import java.time.LocalDateTime;

public class AuditLog {
    private long id;
    private int userId;
    private String action;       // CREATE, READ, UPDATE, DELETE
    private String tableName;
    private int recordId;
    private String description;
    private String ipAddress;
    private LocalDateTime timestamp;

    public AuditLog() {}

    public AuditLog(int userId, String action, String tableName,
                    int recordId, String description) {
        this.userId      = userId;
        this.action      = action;
        this.tableName   = tableName;
        this.recordId    = recordId;
        this.description = description;
        this.timestamp   = LocalDateTime.now();
    }

    public long getId()              { return id; }
    public int getUserId()           { return userId; }
    public String getAction()        { return action; }
    public String getTableName()     { return tableName; }
    public int getRecordId()         { return recordId; }
    public String getDescription()   { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setId(long id)              { this.id = id; }
    public void setUserId(int userId)       { this.userId = userId; }
    public void setAction(String action)    { this.action = action; }
    public void setTableName(String t)      { this.tableName = t; }
    public void setRecordId(int recordId)   { this.recordId = recordId; }
    public void setDescription(String d)   { this.description = d; }
    public void setTimestamp(LocalDateTime t) { this.timestamp = t; }
}
