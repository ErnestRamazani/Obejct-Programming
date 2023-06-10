#include "Entity.h"
#include <cmath>

Entity::Entity(sf::Texture& texture, sf::Vector2f windowSize) : sprite(texture), windowSize(windowSize) {
    // Scale the sprite down
    float scaleX = 0.2f;  // scale factor in the x direction
    float scaleY = 0.2f;  // scale factor in the y direction
    sprite.scale(scaleX, scaleY);
}

Entity::~Entity() {}

void Entity::update(sf::Time deltaTime) {
    // Move the sprite according to its velocity
    sprite.move(velocity * deltaTime.asSeconds());

    // Get the sprite's bounds
    sf::FloatRect bounds = sprite.getGlobalBounds();

    // Check if the sprite has moved outside the window and make it bounce
    if (bounds.left < 0.f) {
        velocity.x = std::abs(velocity.x);
    }
    else if (bounds.left + bounds.width > windowSize.x) {
        velocity.x = -std::abs(velocity.x);
    }

    if (bounds.top < 0.f) {
        velocity.y = std::abs(velocity.y);
    }
    else if (bounds.top + bounds.height > windowSize.y) {
        velocity.y = -std::abs(velocity.y);
    }
}


sf::Vector2f Entity::getVelocity() const {
    return velocity;
}

void Entity::setVelocity(sf::Vector2f velocity) {
    this->velocity = velocity;
}

sf::FloatRect Entity::getBound() const {
    return sprite.getGlobalBounds();
}

void Entity::draw(sf::RenderTarget& target, sf::RenderStates states) const {
    target.draw(sprite, states);
}


Obstacle::Obstacle(sf::Texture& texture) : sprite(texture) {
   
    sprite.setScale(0.1f, 0.1f);
}


Obstacle::~Obstacle() {}

sf::FloatRect Obstacle::getBound() const {
    return sprite.getGlobalBounds();
}

void Obstacle::draw(sf::RenderTarget& target, sf::RenderStates states) const {
    target.draw(sprite, states);
}
void Obstacle::setPosition(float x, float y) {
    sprite.setPosition(x, y);
}
