import { CircleDollarSign } from "lucide-react";
import React from "react";

export default function DashboardLayout({children} : {children: React.ReactNode}){
    return (
        <>
            <div className="container flex h-15 rounded-xl items-center gap-4 shadow-md m-auto bg-zinc-800">
                <CircleDollarSign size={48} className="text-emerald-400" />
                <h1 className="uppercase font-bold text-3xl text-slate-100">Money Manager</h1>
            </div>
            <div className="container items-center gap-4 w-md m-auto mt-2 rounded-xl shadow-md bg-slate-50">
                {children}
            </div>
        </>
    );
}