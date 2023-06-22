#include<iostream>
#include "Enemy.h"

Enemy::Enemy(const sf::Vector2f& position, const sf::Texture& texture) {
    this->position = position;
    velocity = sf::Vector2f(0, 0.01f); // initial velocity can be updated based on AI logic
    health = 100;

    sprite.setTexture(texture);
    sprite.setPosition(this->position);
    sprite.setScale(0.1f, 0.1f);
    hasImpacted = false;
}

Enemy::Enemy(sf::Vector2f position, sf::Texture& texture, float velocityFactor)
    : position(position), hasImpacted(false) {
    sprite.setTexture(texture);
    velocity = sf::Vector2f(0, 0.01f * velocityFactor);
    health = 100;
    sprite.setPosition(this->position);
    sprite.setScale(0.15f, 0.15f);
}


Enemy::~Enemy() {

}
void Enemy::move() {
   
    position += velocity;
    sprite.setPosition(position);
}

void Enemy::draw(sf::RenderWindow& window) {
    window.draw(sprite);
}

void Enemy::handleCollision() {
   
}

void Enemy::update(sf::RenderWindow& window) {
    move();
    sprite.setPosition(position);

  

}

sf::FloatRect Enemy::getBounds() const {
    return sprite.getGlobalBounds();
}

void Enemy::setHealth(int newHealth) {
    health = newHealth;
}

int Enemy::getHealth() const {
    return health;
}

sf::Vector2f Enemy::getPosition() const {
    return position;
}



bool Enemy::getHasImpacted() const {
    return hasImpacted;
}

void Enemy::setHasImpacted(bool impacted) {
    hasImpacted = impacted;
}

void Enemy::setPosition(const sf::Vector2f& newPosition) {
    position = newPosition;
    sprite.setPosition(position);
}

void Enemy::setVelocity(const sf::Vector2f& newVelocity) {
    velocity = newVelocity;
}
void Enemy::increaseSpeed(float factor) {
    sf::Vector2f currentVelocity = velocity;
    setVelocity(currentVelocity * factor);
}