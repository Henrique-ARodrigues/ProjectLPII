package com.banking.security;

import com.banking.transaction.Transaction;

public class InternalStaff {
    
    private RoleType role;
    private int permissionLevel;
    
    public InternalStaff(RoleType role, int permissionLevel) {
        this.role = role;
        this.permissionLevel = permissionLevel;
    }
    
    
    public void reviewTransaction(Transaction t) {
        // Apenas funcionários com nível de permissão 2 ou mais podem aprovar
        if (this.permissionLevel >= 2) {
            t.setStatus("APPROVED");
            System.out.println("Staff " + this.role + " aprovou a transação ID: " + t.getId());
        } else {
            t.setStatus("REJECTED");
            System.out.println("Staff " + this.role + " não tem permissão suficiente. Transação REJEITADA.");
        }
    }

    // Getters
    public RoleType getRole() {
        return role;
    }
    public int getPermissionLevel() {
        return permissionLevel;
    }
}
