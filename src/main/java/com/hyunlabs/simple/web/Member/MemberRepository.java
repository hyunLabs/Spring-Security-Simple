package com.hyunlabs.simple.web.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MemberRepository {

    List<Member> memberList;

    public UUID save(Member member){
        member.setUuid(UUID.randomUUID());
        if(memberList == null) {
            memberList = new ArrayList<Member>();
        }
        memberList.add(member);
        return member.getUuid();
    }

    public Member findById(String id) {
        for(Member member : memberList) {
            if(member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }
}
