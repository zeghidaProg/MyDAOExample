package db;

import java.util.List;

import BO.Chapitre;

/**
 * Created by MOI on 25/11/2015.
 */
public interface IChapitreDAO {

    public long addChapitre(Chapitre chapitre);

    public List<Chapitre> getAllChapitre();

    public Chapitre getChapitre(int id);

    public int updateChapitre(Chapitre chapitre);

    public int deleteChapitre(Chapitre chapitre);

    public int getChapitreCount();






}
