'use client'

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { CircleDollarSign, Router } from "lucide-react";
import { z } from "zod";
import {zodResolver} from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form";
import { Form, FormControl, FormField, FormItem, FormMessage } from "@/components/ui/form";
import { frontendApi } from "@/lib/api";
import { LoginResponseType } from "@/app/api/auth/login/route";
import { useContext, useState } from "react";
import { CustomAlert, CustomAlertType } from "@/components/general/custom-alert";
import { AxiosError } from "axios";
import { AuthContext } from "@/context/auth-context";
import { useRouter } from "next/navigation";

const loginFormSchema = z.object({
    email:z.string().email({ message:"E-mail inválido" }),
    password:z.string().min(1, { message:"Senha inválida" })
});

type LoginFormType = z.infer<typeof loginFormSchema>;

export function LoginForm(){
    const [message, setMessage] = useState(<></>);

    const authContext = useContext(AuthContext);

    const router = useRouter();

    
    const loginForm = useForm<LoginFormType>({
        resolver: zodResolver(loginFormSchema),
        defaultValues: {
            email:"",
            password:""
        }
    });
    async function handleLoginSubmit({ email, password }: LoginFormType){
        
        const data = JSON.stringify({
            email,
            password
        });

        try{
            const result = await frontendApi.post("/auth/login", data);

            const { token, error } = result.data as LoginResponseType;

            if (token) {
                authContext.signIn(token);
                router.push("/dashboard");
            } else{ 
                const message = <CustomAlert type = {CustomAlertType.ERROR} title="Erro ao logar" message={error || "Erro desconhecido"} />;
                setMessage(message);
            }
            

        } catch (e) {
            const axiosError = e as AxiosError;
            const message = <CustomAlert type = {CustomAlertType.ERROR} title="Erro ao logar" message={axiosError.message} />;
        }
    }

    return (
        <>
            <div className="flex pb-60 items-center h-screen">
                <div className="container space-y-3 p-8 max-w-md rounded-xl  bg-gray-50 shadow-md">
                <span className="flex items-center gap-2">
                <CircleDollarSign className="text-slate-500" size={48} />
                <h1 className="uppercase text-slate-600 font-bold text-xl">Money Manager</h1>
                </span>
                <Form {...loginForm}>
                    <form onSubmit={loginForm.handleSubmit(handleLoginSubmit)} className="space-y-3">
                        {message}
                        <FormField
                            control={loginForm.control}
                            name="email"
                            render={({ field }) => {
                                return (
                                    <FormItem>
                                        <FormControl>
                                            <Input type="text" placeholder="Digite seu e-mail" {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )
                            }}
                        />
                        <FormField
                            control={loginForm.control}
                            name="password"
                            render={({ field }) => {
                                return (
                                    <FormItem>
                                        <FormControl>
                                            <Input type="password" placeholder="Digite sua senha" {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )
                            }}
                        />
                        <Button type="submit">Enviar</Button>
                    </form>
                </Form>
                </div>
            </div>
        </>
    )
}