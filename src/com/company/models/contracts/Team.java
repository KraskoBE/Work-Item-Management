package com.company.models.contracts;

import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;

import java.util.List;

public interface Team {

    String getName();

    List<Member> getMembers();

    List<Board> getBoards();
}
