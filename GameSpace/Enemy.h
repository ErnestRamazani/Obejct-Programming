#ifndef ENEMY_H
#define ENEMY_H
#include <SFML/Graphics.hpp>

class Enemy {
private:
    sf::Vector2f position;
    sf::Vector2f velocity;
    int health;
    sf::Sprite sprite;
    bool hasImpacted; 


public:
  
    Enemy(const sf::Vector2f& position, const sf::Texture& texture);
    ~Enemy();


    Enemy(sf::Vector2f position, sf::Texture& texture, float velocityFactor); 
       
    void move();
    void draw(sf::RenderWindow& window);
    void handleCollision();
    void update(sf::RenderWindow& window);
    sf::FloatRect getBounds() const; 
    void setHealth(int newHealth);
    int getHealth() const;
    sf::Vector2f getPosition() const;
    bool getHasImpacted() const;
    void setHasImpacted(bool impacted);
    void setPosition(const sf::Vector2f& newPosition); 
    void setVelocity(const sf::Vector2f& newVelocity); 
    void increaseSpeed(float factor); 


};





#endif // !ENEMY_H
