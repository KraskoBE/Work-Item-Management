package com.company.models.contracts;

import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Team {

    String getName();

    List<Member> getMembers();

    Map<String,Board> getBoards();
}
