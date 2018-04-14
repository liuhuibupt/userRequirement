package com.charmingglobe.gr.entity;

import com.charmingglobe.gr.hibernate.JSONBUserType;
import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by Liuhui on 2018/3/17.
 */
@Entity
@Table(name = "grc_user_request_satellites")
@TypeDef(name = "jsonb", typeClass = JSONBUserType.class)
public class UserRequestSatellites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_num")
    private UserRequest userRequest;

    @Column(name = "request_satellites")
    private String requestSatellites;

    @Column(name = "request_start")
    private Date requestStart;

    @Column(name = "request_end")
    private Date requestEnd;

    @Column(name = "shoot_num")
    private int shootNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public String getRequestSatellites() {
        return requestSatellites;
    }

    public void setRequestSatellites(String requestSatellites) {
        this.requestSatellites = requestSatellites;
    }

    public Date getRequestStart() {
        return requestStart;
    }

    public void setRequestStart(Date requestStart) {
        this.requestStart = requestStart;
    }

    public Date getRequestEnd() {
        return requestEnd;
    }

    public void setRequestEnd(Date requestEnd) {
        this.requestEnd = requestEnd;
    }

    public int getShootNum() {
        return shootNum;
    }

    public void setShootNum(int shootNum) {
        this.shootNum = shootNum;
    }
}




