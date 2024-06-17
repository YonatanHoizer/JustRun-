class camera {
    public int offsetX = 400;
    public int offsetY = 400;
    private character character;

    public camera(character c) {
        this.character = c;
    }

    public void update() {
        if (character.isMovingL) {
            offsetX += 10;
        }
        if (character.isMovingR) {
            offsetX -= 10;
        }
        if (character.isMovingU) {
            offsetY += 10;
        }
        if (character.isMovingD) {
            offsetY -= 10;
        }
    }

    public int getCameraX(int worldX) {
        return worldX + offsetX;
    }

    public int getCameraY(int worldY) {
        return worldY + offsetY;
    }
}
