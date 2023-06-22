#include "PowerUp.h"

PowerUp::PowerUp(const sf::Vector2f& position, const sf::Texture& texture, float speed)
    : position(position), speed(speed) { //
    sprite.setTexture(texture);
    sprite.setPosition(this->position);
    sprite.setScale(0.1f, 0.1f); // Adjust as necessary
}


void PowerUp::draw(sf::RenderWindow& window) {
    window.draw(sprite);
}

sf::FloatRect PowerUp::getBounds() const {
    return sprite.getGlobalBounds();
}


// Implementations for Bomb, DoubleScore, and ExtraLife
Bomb::Bomb(const sf::Vector2f& position, const sf::Texture& texture)
    : PowerUp(position, texture, speed) {}

void Bomb::activate(std::vector<Enemy>& enemies, Player& player, int& score) {
    // Bomb resets all enemies
   
    for (Enemy& enemy : enemies) {
        
        
        enemy.setPosition(sf::Vector2f(rand() % 800, 0));
    }
}

DoubleScore::DoubleScore(const sf::Vector2f& position, const sf::Texture& texture)
    : PowerUp(position, texture, speed) {}

void DoubleScore::activate(std::vector<Enemy>& enemies, Player& player, int& score) {
    score += 20; // Double the current score
}

ExtraLife::ExtraLife(const sf::Vector2f& position, const sf::Texture& texture)
    : PowerUp(position, texture, speed) {}

void ExtraLife::activate(std::vector<Enemy>& enemies, Player& player, int& score) {
    player.setHealth(player.getHealth() + 50); // Adds 50 to player's health
}

void PowerUp::setDirection(const sf::Vector2f& new_direction) {
    direction = new_direction;
}

void PowerUp::setSpeed(float new_speed) {
    speed = new_speed;
}

sf::Vector2f PowerUp::getPosition() const {
    return position;
}

// update function
void PowerUp::update() {
    position += speed * direction;
    sprite.setPosition(position);  // Update sprite position
}
