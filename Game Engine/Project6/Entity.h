#ifndef ENTITY_H
#define ENTITY_H

#include <SFML/Graphics.hpp>

class Entity : public sf::Drawable {
private:
    sf::Sprite sprite;
    sf::Vector2f velocity;
    sf::Vector2f windowSize; 
    // This function must be defined because we're inheriting sf::Drawable
    void draw(sf::RenderTarget& target, sf::RenderStates states) const override;

public:
    Entity(sf::Texture& texture, sf::Vector2f windowSize); 
    ~Entity();

    void update(sf::Time deltaTime);
    sf::Vector2f getVelocity() const;
    void setVelocity(sf::Vector2f velocity);
    sf::FloatRect getBound() const;
    void bounce();
};

class Obstacle : public sf::Drawable {
private:
    sf::Sprite sprite; 

    void draw(sf::RenderTarget& target, sf::RenderStates states) const override; 

public:
    Obstacle(sf::Texture& texture);
    ~Obstacle();

    sf::FloatRect getBound() const;
    void setPosition(float x, float y);
};

#endif
