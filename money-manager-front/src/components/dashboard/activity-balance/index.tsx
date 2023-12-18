'use client';

import { ActivityTableContext } from "@/context/activity-table-context";
import { useContext } from "react";

export function ActivityBalance(){
    const activityTableContext = useContext(ActivityTableContext);

    const balance = activityTableContext.balance;
     
    var balanceClass = balance < 0 ? "text-red-500" : "text-emerald-500";

    return(
        <>
            <div className="flex gap-4 p-4 px-8 py-12 text-xl font-bold">
                <p>Total: </p>
                <p className= {balanceClass}>R$ {balance.toLocaleString("pt-BR", {minimumFractionDigits: 2, maximumFractionDigits: 2})}</p>
            </div>
        </>
    );
}