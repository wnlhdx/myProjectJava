/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ 
/*     */ class Road {
/*     */   public static final int LENGTH = 400;
/*     */   
/*     */   public static final int MAXSPEED = 5;
/*     */   
/*  67 */   private int[] speed = new int[400];
/*     */   
/*  68 */   private Color[] colors = new Color[400];
/*     */   
/*     */   private int count;
/*     */   
/*     */   public Road() {
/*  69 */     for (byte b = 0; b < 'Ɛ'; ) {
/*  69 */       this.speed[b] = -1;
/*  69 */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update(double paramDouble1, double paramDouble2) {
/*  74 */     int i = 0;
/*  75 */     while (i < 400 && this.speed[i] == -1)
/*  76 */       i++; 
/*  77 */     while (i < 400) {
/*  79 */       if (Math.random() <= paramDouble1 && this.speed[i] > 0) {
/*  80 */         this.speed[i] = this.speed[i] - 1;
/*  81 */       } else if (this.speed[i] < 5) {
/*  82 */         this.speed[i] = this.speed[i] + 1;
/*     */       } 
/*  83 */       int j = i + 1;
/*  84 */       while (j < 400 && this.speed[j] == -1)
/*  85 */         j++; 
/*  86 */       if (j < 400)
/*  88 */         if (this.speed[i] >= j - i)
/*  89 */           this.speed[i] = j - i - 1;  
/*  91 */       if (this.speed[i] > 0) {
/*  93 */         if (i + this.speed[i] < 400) {
/*  95 */           int k = i + this.speed[i];
/*  96 */           this.speed[k] = this.speed[i];
/*  97 */           this.colors[k] = this.colors[i];
/*     */         } 
/*  99 */         this.speed[i] = -1;
/*     */       } 
/* 101 */       i = j;
/*     */     } 
/* 103 */     if (Math.random() <= paramDouble2 && this.speed[0] == -1) {
/* 105 */       this.speed[0] = (int)(5.99D * Math.random());
/* 106 */       this.colors[0] = (++this.count % 10 == 0) ? Color.red : Color.black;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void paint(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3) {
/* 112 */     paramGraphics.setColor(Color.WHITE);
/* 113 */     paramGraphics.fillRect(0, paramInt1, 400 * paramInt3, paramInt3);
/* 114 */     for (byte b = 0; b < 'Ɛ'; b++) {
/* 116 */       if (this.speed[b] >= 0) {
/* 118 */         paramGraphics.setColor(this.colors[b]);
/* 119 */         paramGraphics.fillRect(b * paramInt2, paramInt1, paramInt3, paramInt3);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\work\code\github\corejava\corejava第10版\v1ch02\RoadApplet\RoadApplet.jar!\Road.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */