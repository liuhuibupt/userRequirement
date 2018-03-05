package com.charmingglobe.gr.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by PANZHENG on 2018/2/7.
 */
@Entity
@Table(name = "grc_user_action")
public class UserAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_id")
    private Cavalier executor;

    @Column(name = "action_name")
    private String actionName;

    @Column(name = "jump_link")
    private String jumpLink;

    @Column(name = "action_time")
    private Date actionTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cavalier getExecutor() {
        return executor;
    }

    public void setExecutor(Cavalier executor) {
        this.executor = executor;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getJumpLink() {
        return jumpLink;
    }

    public void setJumpLink(String jumpLink) {
        this.jumpLink = jumpLink;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }
}
