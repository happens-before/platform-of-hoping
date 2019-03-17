package com.xupt.edu.zwy.platformofhoping.service;

import com.xupt.edu.zwy.platformofhoping.dto.OrganizerReq;
import com.xupt.edu.zwy.platformofhoping.model.Organizer;

public interface IOrganizerService {
    boolean isRightOrganizer (OrganizerReq organizerReq);

    int registerOrganizer(Organizer organizer);
}
