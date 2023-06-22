#ifndef POWERUP_H
#define POWERUP_H

#include <SFML/Graphics.hpp>
#include "Player.h"
#include "Enemy.h"

class PowerUp {
protected:
    sf::Vector2f position;
    sf::Sprite sprite;
    float speed;
    sf::Vector2f direction;

public:
    PowerUp(const sf::Vector2f& position, const sf::Texture& texture, float speed);
    virtual void activate(std::vector<Enemy>& enemies, Player& player, int& score) = 0;  // Pure virtual function
    void draw(sf::RenderWindow& window);
    void update();
    void setDirection(const sf::Vector2f& new_direction);
    void setSpeed(float new_speed);
    sf::Vector2f getPosition() const;
    sf::FloatRect getBounds() const;
};


class Bomb : public PowerUp {
public:
    Bomb(const sf::Vector2f& position, const sf::Texture& texture);
    void activate(std::vector<Enemy>& enemies, Player& player, int& score) override;
};

class DoubleScore : public PowerUp {
public:
    DoubleScore(const sf::Vector2f& position, const sf::Texture& texture);
    void activate(std::vector<Enemy>& enemies, Player& player, int& score) override;
};

class ExtraLife : public PowerUp {
public:
    ExtraLife(const sf::Vector2f& position, const sf::Texture& texture);
    void activate(std::vector<Enemy>& enemies, Player& player, int& score) override;
};

#endif // POWERUP_H     