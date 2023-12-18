'use client'

import { columns } from "./columns"
import { DataTable } from "./data-table"
import { useContext } from "react";
import { ActivityTableContext } from "@/context/activity-table-context";

 

export function ActivityTable() {

  const activityTableContext = useContext(ActivityTableContext);

  const activities = activityTableContext.activities;
 
  return (
    <div className="container mx-auto">
      <DataTable columns={columns} data={activities} />
    </div>
  );
}