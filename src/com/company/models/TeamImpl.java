package com.company.models;


import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;

import java.util.List;

public class TeamImpl implements Team {
    private String name;
    private List<Member> members;
    private List<Board> boards;
}
