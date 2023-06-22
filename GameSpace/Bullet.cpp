#include "Bullet.h"


Bullet::Bullet(const sf::Vector2f& position, const sf::Vector2f& velocity, const sf::Texture& texture) {
    this->position = position;
    this->velocity = sf::Vector2f(0,-0.9f);

    sprite.setTexture(texture);
    sprite.setPosition(this->position);
    sprite.setScale(0.2f, 0.2f);
}

Bullet::~Bullet() {

}

void Bullet::move() {
    // Bullets typically move in a straight line, so we don't need any complex logic here
    position += velocity;
    sprite.setPosition(position);
}

void Bullet::draw(sf::RenderWindow& window) {
    window.draw(sprite);
}

void Bullet::handleCollision() {
    // implementation depends on what it collides with (enemy, player, etc.)
}

sf::FloatRect Bullet::getBounds() const {
    return sprite.getGlobalBounds();
}
sf::Vector2f Bullet::getPosition() const {
    return sprite.getPosition();
}

void Bullet::update() {
    move(); 
    sprite.setPosition(position); 
}

bool Bullet::operator==(const Bullet& other) const {
    // Compare relevant fields for equality
    return position == other.position && velocity == other.velocity;
    // Add more fields to compare if needed
}
