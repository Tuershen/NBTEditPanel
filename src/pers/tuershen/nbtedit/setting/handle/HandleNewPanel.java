package pers.tuershen.nbtedit.setting.handle;


import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList;
import org.bukkit.inventory.Inventory;
import pers.tuershen.nbtedit.panel.edit.entity.EditArrayEntity;
import pers.tuershen.nbtedit.panel.edit.entity.EditCompoundEntity;
import pers.tuershen.nbtedit.panel.edit.item.EditArrayItem;
import pers.tuershen.nbtedit.panel.edit.item.EditCompoundItem;
import pers.tuershen.nbtedit.panel.edit.tile.EditArrayEntityTile;
import pers.tuershen.nbtedit.panel.edit.tile.EditCompoundEntityTile;
import pers.tuershen.nbtedit.setting.NewPanel;


public enum HandleNewPanel implements NewPanel {

    CREATE_ITEM_PANEL(EditObjectTypeEnum.ITEM){
        @Override
        public Inventory newPanel(CreatePanel createPanel) {
            if (createPanel.getTagBase() != null){
                TagBase tagBase = createPanel.getTagBase();
                if (tagBase instanceof TagCompound){
                   return new EditCompoundItem(
                           createPanel.getUuid(),
                           createPanel.getItemStack(),
                           (TagCompound) tagBase,
                           createPanel.getEdit(),
                           createPanel.getKey(),
                           createPanel.getSlot()).newOpenPanel();
                }else if (tagBase instanceof TagList){
                    return new EditArrayItem(
                            createPanel.getUuid(),
                            createPanel.getItemStack(),
                            (TagList) tagBase,
                            createPanel.getEdit(),
                            createPanel.getKey(),
                            createPanel.getSlot()).newOpenPanel();
                }
            }
            return createPanel.getEdit().getInventory();
        }

    },
    CREATE_CHUNK_PANEL(EditObjectTypeEnum.TILE_ENTITY) {
        public Inventory newPanel(CreatePanel createPanel) {
            if (createPanel.getTagBase() != null){
                TagBase tagBase = createPanel.getTagBase();
                if (tagBase instanceof TagCompound){
                    return new EditCompoundEntityTile(
                            createPanel.getUuid(),
                            (TagCompound) tagBase,
                            createPanel.getEdit(),
                            createPanel.getKey(),
                            createPanel.getSlot(),
                            createPanel.getTileEntityCompoundApi()).newOpenPanel();
                }else if (tagBase instanceof TagList){
                    return new EditArrayEntityTile(
                            createPanel.getUuid(),
                            (TagList) tagBase,
                            createPanel.getEdit(),
                            createPanel.getKey(),
                            createPanel.getSlot(),
                            createPanel.getTileEntityCompoundApi()).newOpenPanel();
                }
            }
            return createPanel.getEdit().getInventory();
        }

    },
    CREATE_ENTITY_PANEL(EditObjectTypeEnum.ENTITY) {
        public Inventory newPanel(CreatePanel createPanel) {
            if (createPanel.getTagBase() != null){
                TagBase tagBase = createPanel.getTagBase();
                if (tagBase instanceof TagCompound){
                    return new EditCompoundEntity(
                            createPanel.getUuid(),
                            (TagCompound) tagBase,
                            createPanel.getEdit(),
                            createPanel.getKey(),
                            createPanel.getSlot(),
                            createPanel.getEntityNBTTagCompoundApi()).newOpenPanel();
                }else if (tagBase instanceof TagList){
                    return new EditArrayEntity(
                            createPanel.getUuid(),
                            (TagList) tagBase,
                            createPanel.getEdit(),
                            createPanel.getKey(),
                            createPanel.getSlot(),
                            createPanel.getEntityNBTTagCompoundApi()).newOpenPanel();
                }
            }
            return createPanel.getEdit().getInventory();
        }

    };

    private EditObjectTypeEnum typeEnum;

    HandleNewPanel(EditObjectTypeEnum item) {
        this.typeEnum = item;
    }

    public static HandleNewPanel getInstance(EditObjectTypeEnum  objectTypeEnum){
        for (HandleNewPanel handleNewPanel : HandleNewPanel.values()){
            if (handleNewPanel.typeEnum == objectTypeEnum)return handleNewPanel;
        }
        return null;
    }

}
