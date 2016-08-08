package com.wix.mediaplatform.dto.image;

import com.wix.mediaplatform.dto.BaseDTO;

import java.util.Set;

public class ImageDTO extends BaseDTO {

    private int width;

    private int height;

    private Set<Square> faces;

    public ImageDTO() {
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Set<Square> getFaces() {
        return faces;
    }

    private class Square {
        private int x;
        private int y;
        private int width;
        private int height;

        public Square() {
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
