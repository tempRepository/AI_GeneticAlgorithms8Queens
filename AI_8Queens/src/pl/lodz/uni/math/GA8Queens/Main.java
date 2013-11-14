package pl.lodz.uni.math.GA8Queens;

public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        initComponents();
        setTitle("8 Queens - AI - Filip Jerzyna");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taOutput = new javax.swing.JTextArea();
        btTest = new javax.swing.JButton();
        lblIteration = new javax.swing.JLabel();
        tfIteration = new javax.swing.JTextField();
        lblPopulationQuantity = new javax.swing.JLabel();
        tfPopulationQuantity = new javax.swing.JTextField();
        lblMagicSquareSide = new javax.swing.JLabel();
        tfMagicSquareSide = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taOutput.setColumns(20);
        taOutput.setRows(5);
        jScrollPane1.setViewportView(taOutput);

        btTest.setText("Test");
        btTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              //  btTestActionPerformed(evt);
            }
        });

        lblIteration.setText("Ilość iteracji:");

        tfIteration.setText("1000");

        lblPopulationQuantity.setText("Wielkość populacji:");

        tfPopulationQuantity.setText("10");

        lblMagicSquareSide.setText("Bok magicznego kwadratu:");

        tfMagicSquareSide.setText("3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addComponent(btTest))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblIteration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfIteration))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPopulationQuantity)
                                    .addComponent(tfPopulationQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblMagicSquareSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfMagicSquareSide))))
                        .addGap(0, 60, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIteration)
                    .addComponent(lblPopulationQuantity)
                    .addComponent(lblMagicSquareSide))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfIteration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPopulationQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMagicSquareSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btTest)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
    private void btTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTestActionPerformed
 
        taOutput.setText("");
        int greatnessBefore = -1;
        int greatnessAfter = -1;
        Population population = new Population(Integer.parseInt(tfPopulationQuantity.getText()), Integer.parseInt(tfMagicSquareSide.getText()));
        int best = -1;
        boolean worthReplaying = true;
        while (worthReplaying) {            
            worthReplaying = false;
            for (int i = 0; i < Integer.parseInt(tfIteration.getText()) && best == -1; i++) {
                best = population.makeNewPopulation();
                if (i == 0) {
                    greatnessBefore = population.getGreatness();
                }
                if (i == Integer.parseInt(tfIteration.getText()) - 1) {
                    greatnessAfter = population.getGreatness();
                }
                if (best != -1) {
                    taOutput.append("\n\n" + population.getChromosomes().get(best).toString());
                }
                
            }
            if (best == -1 && greatnessBefore < greatnessAfter) {
                // taOutput.append("\nGREATNESS:"+population.getGreatness());
                worthReplaying = true;
                taOutput.append("\n Zwiększono ilość iteracji! \n");
            } else if (best == -1) {
                taOutput.append("\n Nie wygenerowano magicznego kwadratu w zadanej ilości iteracji \n");
            }
        }

        
        
    }
    */
   

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btTest;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIteration;
    private javax.swing.JLabel lblMagicSquareSide;
    private javax.swing.JLabel lblPopulationQuantity;
    private javax.swing.JTextArea taOutput;
    private javax.swing.JTextField tfIteration;
    private javax.swing.JTextField tfMagicSquareSide;
    private javax.swing.JTextField tfPopulationQuantity;
    // End of variables declaration//GEN-END:variables
}
