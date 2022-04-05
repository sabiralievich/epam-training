package heroes.creatures;

public interface Actions {
    void attack();

    void move();

    void takeDamage(int damage);

    void die();

    void superAction();
}
