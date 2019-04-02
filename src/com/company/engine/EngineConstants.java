package com.company.engine;

public class EngineConstants {

    // Commands
    static final String CreateTeamCommand = "CreateTeam";
    static final String CreateMemberCommand = "CreateMember";
    static final String CreateBoardCommand = "CreateBoard";

    // Error messages
    static final String InvalidCommandErrorMessage = "Invalid command name: %s";
    static final String TeamExistsErrorMessage = "Team %s already exists";
    static final String MemberExistsErrorMessage = "Member %s already exists";
    static final String BoardExistsErrorMessage = "Board %s already exists";

    // Success messages
    static final String TeamCreatedSuccessMessage = "Team %s created";
    static final String MemberCreatedSuccessMessage = "Member %s created";
    static final String BoardCreatedSuccessMessage = "Board %s created";
}
