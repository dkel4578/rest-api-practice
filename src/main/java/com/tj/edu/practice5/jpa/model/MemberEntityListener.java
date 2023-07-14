package com.tj.edu.practice5.jpa.model;

import com.tj.edu.practice5.jpa.repository.MemberLogHistoryRepository;
import com.tj.edu.practice5.jpa.util.SpringBeanUtils;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class MemberEntityListener {
    @PostPersist
    @PreUpdate
    public void afterMemberSave(Object o){
        MemberLogHistoryRepository memberLogHistoryRepository =
                SpringBeanUtils.getBean(MemberLogHistoryRepository.class);
        if(o instanceof Member) {
            Member member = (Member) o;

            MemberLogHistory memberLogHistory =
                    MemberLogHistory.builder()
                            .member(member)
                            .name(member.getName())
                            .email(member.getEmail())
                            .build();

            memberLogHistoryRepository.save(memberLogHistory);
        }
    }
}
