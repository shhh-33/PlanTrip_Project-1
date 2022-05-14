package com.plantrip.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "attendee")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendee_id")
    private Long attendeeId;

    // 1(참석자) - N(여러개의 여행 참여)
    @JoinColumn(name = "trip_id")
    @ManyToOne
    private Trip trip;

    // N(여러 명 참석자들의 id) - 1(참석자 명단)
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "attendee_type")
    private AttendeeType attendeeType;

    public enum AttendeeType {
        LEADER, ATTENDEE
    }
}
