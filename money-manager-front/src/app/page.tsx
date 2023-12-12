import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { CircleDollarSign } from "lucide-react"


export default function Home() {
  return (
    <main>
      <div className="flex pb-60 items-center h-screen">
        <div className="container space-y-3 p-8 max-w-md rounded-xl  bg-gray-50 shadow-md">
          <span className="flex items-center gap-2">
            <CircleDollarSign className="text-slate-500" size={48} />
            <h1 className="uppercase text-slate-600 font-bold text-xl">Money Manager</h1>
          </span>
          <Input type="email" placeholder="Digite o seu e-mail" />
          <Input type="password" placeholder="Digite a sua senha" />
          <Button>Enviar</Button>
        </div>
      </div>
    </main>
  )
}
