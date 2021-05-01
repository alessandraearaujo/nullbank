package com.nullbank.controllers;

import com.nullbank.models.Cliente;
import com.nullbank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {

    // Faz a injeção de dependências, criando uma nova instância da classe
    @Autowired
    private ClienteRepository cli;


    // Requisição que retorna o formulário
    @RequestMapping(value="/cadastrarCliente", method=RequestMethod.GET)
    public String form() {
        return "formCliente";
    }

    // Requesição que envia dados, salvando-os no banco de dados utilizando o crud extends da interface
    @RequestMapping(value="/cadastrarCliente", method=RequestMethod.POST)
    public String form(Cliente cliente) {
        // Salva o cliente no banco de dados
        cli.save(cliente);
        return "redirect:/cadastrarCliente";
    }

    @RequestMapping(value="/listarClientes")
    // Requisição exibe clientes cadastrados na mesma view do formCliente
    public ModelAndView listaClientes() {
        ModelAndView mv = new ModelAndView("formCliente");
        Iterable<Cliente> clientes = cli.findAll();
        //reconhece a palavra "clientes" da view
        mv.addObject("clientes", clientes);
        return mv;
    }
}


