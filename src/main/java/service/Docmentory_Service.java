package service;
import entity.Docmentory;

public interface Docmentory_Service extends basicService<Docmentory>{
    public Docmentory getbyid(Docmentory docmentory);
    public void insertjl(Docmentory doc);
}