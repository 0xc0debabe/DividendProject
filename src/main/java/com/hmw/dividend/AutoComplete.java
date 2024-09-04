package com.hmw.dividend;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

public class AutoComplete {

    private Trie trie = new PatriciaTrie();

    public void add(String s) {
        this.trie.put(s, "world");
    }
}
