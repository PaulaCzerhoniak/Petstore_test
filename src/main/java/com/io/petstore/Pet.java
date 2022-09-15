package com.io.petstore;

import java.util.ArrayList;
import java.util.List;

public class Pet {
    private String id;
    private Category category;
    private String name;
    private List<String> photoUrls = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private Status status;

    private Pet() {
    }


    public String getId() {
        return this.id;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getPhotoUrls() {
        return this.photoUrls;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public static class Builder {
        private String id;
        private Category category;
        private String name;
        private List<String> photoUrls = new ArrayList<>();
        private List<Tag> tags = new ArrayList<>();
        private Status status;

        public Builder() {
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder inCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPhotoUrls(List<String> photoUrls) {
            this.photoUrls = photoUrls;
            return this;
        }

        public Builder withTags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Pet build() {
            Pet pet = new Pet();
            pet.id = this.id;
            pet.name = this.name;
            pet.category = this.category;
            pet.photoUrls = this.photoUrls;
            pet.tags = this.tags;
            pet.status = this.status;
            return pet;
        }
    }
}
