#ifndef BULLET_H
#define BULLET_H
#include <SFML/Graphics.hpp>

class Bullet {
private:
    sf::Vector2f position;
    sf::Vector2f velocity;
    sf::Sprite sprite;

public:
    Bullet(const sf::Vector2f& position, const sf::Vector2f& velocity, const sf::Texture& texture);
    ~Bullet(); 
    void move();
    void draw(sf::RenderWindow& window);
    void handleCollision();
    sf::FloatRect getBounds() const;
    void update(); 
    sf::Vector2f getPosition() const;
    bool operator==(const Bullet& other) const; 

};


#endif // !BULLET_H
