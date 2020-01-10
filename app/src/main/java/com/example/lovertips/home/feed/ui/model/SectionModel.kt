package com.example.lovertips.home.feed.ui.model

class SectionModel(_sectionLabel: String?, _itemArrayList:ArrayList<ItemModel>) {
    var sectionLabel: String? = _sectionLabel

        // Custom Getter
        get() {
            return field
        }

        set(value) {
            field = value	 // Calls the setter recursively
        }

    var itemArrayList: ArrayList<ItemModel> = _itemArrayList
        // Custom Getter
        get() {
            return field
        }

        set(value) {
            field = value	 // Calls the setter recursively
        }

}


