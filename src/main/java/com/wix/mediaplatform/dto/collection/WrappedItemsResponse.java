package com.wix.mediaplatform.dto.collection;

public class WrappedItemsResponse {

    private ItemAndResponse[] items;

    public ItemAndResponse[] getItems() {
        return items;
    }

    public class ItemAndResponse {

        private ItemDTO object;

        private ResponseWrapper result;

        public ItemDTO getObject() {
            return object;
        }

        public ResponseWrapper getResult() {
            return result;
        }
    }

}
