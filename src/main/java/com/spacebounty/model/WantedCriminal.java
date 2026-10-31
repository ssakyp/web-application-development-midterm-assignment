package com.spacebounty.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wanted_criminals")
public class WantedCriminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nickname;
    private BigDecimal reward;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String textNote;
    private Integer difficulty;
    private boolean armed;
    private boolean hasCrew;
    private boolean escapeMaster;

    @OneToMany(mappedBy = "criminal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HuntLog> huntLogs = new ArrayList<>();

    protected WantedCriminal() {
    }

    public WantedCriminal(String name, String nickname, BigDecimal reward, BigDecimal latitude,
                          BigDecimal longitude, String textNote, Integer difficulty, boolean armed,
                          boolean hasCrew, boolean escapeMaster) {
        this.name = name;
        this.nickname = nickname;
        this.reward = reward;
        this.latitude = latitude;
        this.longitude = longitude;
        this.textNote = textNote;
        this.difficulty = difficulty;
        this.armed = armed;
        this.hasCrew = hasCrew;
        this.escapeMaster = escapeMaster;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getNickname() { return nickname; }
    public BigDecimal getReward() { return reward; }
    public BigDecimal getLatitude() { return latitude; }
    public BigDecimal getLongitude() { return longitude; }
    public String getTextNote() { return textNote; }
    public Integer getDifficulty() { return difficulty; }
    public boolean isArmed() { return armed; }
    public boolean isHasCrew() { return hasCrew; }
    public boolean isEscapeMaster() { return escapeMaster; }
    public List<HuntLog> getHuntLogs() { return huntLogs; }
}
