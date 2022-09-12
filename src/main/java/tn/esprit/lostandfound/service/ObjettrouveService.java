package tn.esprit.lostandfound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.lostandfound.dao.ObjettrouveDao;
import tn.esprit.lostandfound.entity.Objettrouve;
import tn.esprit.lostandfound.exception.ObjettrouveNotFoundException;

import java.util.List;

@Service
public class ObjettrouveService {
    private final ObjettrouveDao objettrouveDao;
    @Autowired
    public ObjettrouveService(ObjettrouveDao objettrouveDao){
        this.objettrouveDao=objettrouveDao;
    }
    public Objettrouve addObjetTrouve(Objettrouve objettrouve){
        return objettrouveDao.save(objettrouve);
    }

    public List<Objettrouve> findAllObjettrouve(){
        return objettrouveDao.findAll();
    }

    public void updateObjettrouve(String nom,String description, Long id){
         objettrouveDao.updateObjet(nom,description,id);
    }

    public Objettrouve findObjettrouveById(Long id){
        return objettrouveDao.findObjettrouveById(id)
                .orElseThrow(()-> new ObjettrouveNotFoundException("objet trouvé by id=" + id + "was not found"));
    }

    public void deleteObjettrouve(Long id){
        objettrouveDao.deleteById(id);
    }

}
