"use client"
 
import { Button } from "@/components/ui/button"
import { ColumnDef } from "@tanstack/react-table"
import { RemoveButton } from "./remove-button"
 
// This type is used to define the shape of our data.
// You can use a Zod schema here if you want.
export type Activity = {
  id: string,
  date: Date,
  description: string,
  value: number,
  type: "expense" | "revenue"
}
 
export const columns: ColumnDef<Activity>[] = [
  {
    accessorKey: "date",
    header: "Data",
    cell: ({ row } ) => {
      const aDate = new Date(row.getValue("date")) as Date;
      const formatedDate = `${aDate.getDate()}/${aDate.getMonth() + 1}/${aDate.getFullYear()}`; 
      return <p>{formatedDate}</p>
    }
  },
  {
    accessorKey: "description",
    header: "Descrição",
  },
  {
    accessorKey: "value",
    header: "Valor",
    cell: ({ row }) => {
      const aValue = row.getValue("value") as number;
      const type = row.getValue("type");
      const formatedValue = aValue.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
      const valueClass = (type === "REVENUE") ? "text-emerald-500" : "text-red-500";
      return <p className={valueClass}>{formatedValue}</p>
    }
  },
  {
    accessorKey: "type",
    header: "Tipo",
    cell: ({ row }) => {
      const type = row.getValue("type");
      const formatedValue = (type === "REVENUE") ? "Entrada" : "Saída";
      return <p>{formatedValue}</p>
    }
  },
  {
    id: "actions",
    header: "Ações",
    cell: ({ row }) => {
      const id = row.original.id;
      return <RemoveButton id={id} />
    }
  }
]