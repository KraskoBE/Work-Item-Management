package com.company.models.contracts;

import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;

import java.util.Map;

public interface Team {

    String getName();

    Map<String,Member> getMembers();

    Map<String,Board> getBoards();
}
