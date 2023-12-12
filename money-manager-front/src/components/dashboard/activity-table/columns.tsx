"use client"
 
import { Button } from "@/components/ui/button"
import { ColumnDef } from "@tanstack/react-table"
 
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
      const aDate = row.getValue("date") as Date;
      const formatedDate = aDate.getDate() + "/" + (aDate.getMonth() + 1)  + "/" + aDate.getFullYear(); 
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
      const formatedValue = aValue.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
      const valueClass = row.getValue("type") === "expense" ? "text-red-500" : "text-emerald-500";
      return <p className={valueClass}>{formatedValue}</p>
    }
  },
  {
    accessorKey: "type",
    header: "Tipo",
  },
  {
    id: "actions",
    header: "Ações",
    cell: ({row}) => {
      return <Button variant="ghost">Remover</Button>
    }
  }
]