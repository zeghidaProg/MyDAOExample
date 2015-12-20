package BO;

/**
 * Created by MOI on 25/11/2015.
 */
public class Chapitre {

    private int _id;
    private String chapitreName;


    public Chapitre(int _id, String chapitreName) {
        this._id = _id;
        this.chapitreName = chapitreName;
    }

    public Chapitre(String chapitreName) {
        this.chapitreName = chapitreName;
    }

    public Chapitre() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getChapitreName() {
        return chapitreName;
    }

    public void setChapitreName(String chapitreName) {
        this.chapitreName = chapitreName;
    }
}
