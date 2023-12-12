import { Activity, columns } from "./columns"
import { DataTable } from "./data-table"

const data: Activity[] = [
  {
    id: "1",
    date: new Date("07-25-2023"),
    description: "Pagamento de conta de luz",
    value: 250.00,
    type: "expense"
  },
  {
    id: "2",
    date: new Date("07-20-2023"),
    description: "Pagamento de conta de água",
    value: 100.00,
    type: "expense"
  },
  {
    id: "3",
    date: new Date("07-01-2023"),
    description: "Aluguel",
    value: 200.00,
    type: "expense"
  },
  {
    id: "5",
    date: new Date("07-05-2023"),
    description: "Salário",
    value: 2500.00,
    type: "revenue"
  },
]
 
function getData(): Activity[] {
  return data;
}
 
export function ActivityTable() {
  const data = getData()
 
  return (
    <div className="container mx-auto">
      <DataTable columns={columns} data={data} />
    </div>
  );
}