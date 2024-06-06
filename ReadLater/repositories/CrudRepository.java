package repositories;

import java.util.List;

import interfaces.WithId;

public abstract class CrudRepository<T extends WithId> {
    protected List<T> repo;

    public CrudRepository(List<T> repo) {
        this.repo = repo;
    }

    public void add(T obj) {
        repo.add(obj);
    }

    public List<T> getAll() {
        return repo;
    }

    public T getById(int id) {
        for (T obj : repo) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }

    public void update(T obj) {
    }

    public void delete(T obj) {
        repo.remove(obj);
    }
}