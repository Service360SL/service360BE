package com.service360.group50.request;

import java.time.LocalDate;

public class JobRepliesRequest {
    private Long serviceproviderId;
    private String replymessage;
    private LocalDate replydate;
    private Long jobId;

    // Getters for the fields
    public Long getServiceproviderId() { return serviceproviderId; }

    public String getReplymessage() { return replymessage; }

    public LocalDate getReplydate() { return replydate; }

    public Long getJobId() { return jobId; }
}
