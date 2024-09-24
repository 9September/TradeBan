package yd.kingdom.tradeban;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class InventoryClickListener implements Listener {

    private final String TARGET_INVENTORY_NAME = ":offset_-48::trade:";

    private final List<String> BLOCKED_NAMES = Arrays.asList("0차", "1차", "2차", "3차", "4차", "5차");

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // 인벤토리 제목 확인
        String inventoryTitle = event.getView().getTitle();

        if (!inventoryTitle.equals(TARGET_INVENTORY_NAME)) {
            return; // 대상 인벤토리가 아니면 무시
        }

        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || !clickedItem.hasItemMeta()) {
            return; // 클릭한 아이템이 없거나 메타가 없으면 무시
        }

        ItemMeta meta = clickedItem.getItemMeta();
        if (!meta.hasDisplayName()) {
            return; // 아이템에 표시 이름이 없으면 무시
        }

        String displayName = meta.getDisplayName();

        // 아이템 이름에 차수 단어가 포함되어 있는지 확인
        for (String name : BLOCKED_NAMES) {
            if (displayName.contains(name)) {
                event.setCancelled(true); // 클릭 이벤트 취소
                event.getWhoClicked().sendMessage(ChatColor.RED + "이 아이템은 거래할 수 없습니다.");
                break;
            }
        }
    }

}
