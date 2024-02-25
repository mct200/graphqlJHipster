package com.groupeisi.service;

import com.groupeisi.entities.Etudiant;
import com.groupeisi.repository.EtudiantRepository;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class EtudiantServiceImp implements EtudiantService {

    private final EtudiantRepository etudiantRepository;

    public EtudiantServiceImp(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Override
    public Etudiant creer(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public List<Etudiant> lire() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant modifier(int id, Etudiant etudiant) {
        return etudiantRepository
            .findById(id)
            .map(e -> {
                e.setPrenom(etudiant.getPrenom());
                e.setNom(etudiant.getNom());
                return etudiantRepository.save(e);
            })
            .orElseThrow(() -> new RuntimeException("Etudiant non trouve !"));
    }

    @Override
    public String supprimer(int id) {
        etudiantRepository.deleteById(id);
        return "Etudiant supprime avec succes !";
    }
}
