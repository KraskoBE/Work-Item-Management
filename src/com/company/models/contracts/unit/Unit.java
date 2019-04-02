package com.company.models.contracts.unit;

import com.company.models.workItem.WorkItemBase;


import java.util.List;

public interface Unit {
    String getName() ;

    List<WorkItemBase> getItems() ;

   List<String> getActivityHistory();

}
