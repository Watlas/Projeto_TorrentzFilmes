package br.com.watlas.util;

import br.com.watlas.dal.AdministradorDal;
import br.com.watlas.dal.ContrataDal;
import br.com.watlas.modal.Administrador;
import br.com.watlas.modal.Contrata;

import java.util.ArrayList;
import java.util.List;

public class teste {
    public static void main(String[] args) throws Exception {
        Contrata administrador = new Contrata();
        ContrataDal administradorDal = new ContrataDal();
//        administrador.setAdm_nome("watlas");
//        administrador.setAdm_email("Watlas@hot.com");
//        administrador.setSenha("123");
//        administradorDal.Add(administrador);
        List<Contrata> listaa;
        listaa = administradorDal.getAll();

        for (Contrata adm: listaa
             ) {
            System.out.println(adm.toString());
        }

    }

}
