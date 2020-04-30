package com.henan;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.henan.service.DataService;

public class Hn
{
    private final static Logger log = Logger.getLogger(DataService.class);
    
    private JFrame frame;
    
    private static ApplicationContext ctx = null;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Hn window = new Hn();
                    window.frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the application.
     */
    public Hn()
    {
        initialize();
    }
    
    private JTextPane end = new JTextPane();
    
    private JTextPane code = new JTextPane();
    
    private JTextPane start = new JTextPane();
    
    private JLabel lblNewLabel = new JLabel("\u63D2\u5165\u6761\u6570\uFF1A");
    
    JLabel infolab = new JLabel("");
    
    JButton btnNewButton = new JButton(" \u5F00\u59CB\u540C\u6B65\u6570\u636E");
    
    private boolean flag = false;
    
    private GoThread t = null;
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        
        frame = new JFrame();
        frame.getContentPane().setEnabled(false);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                btnNewButton.setEnabled(false);
                if (ctx == null)
                {
                    ctx =
                        new ClassPathXmlApplicationContext(
                            "classpath:spring-context.xml");
                }
                flag = true;
                
                t = new GoThread();
                t.start();
                
            }
        });
        btnNewButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent arg0)
            {
                
            }
        });
        frame.setVisible(true);
        btnNewButton.setBounds(125, 192, 155, 23);
        frame.getContentPane().add(btnNewButton);
        
        start.setBounds(109, 77, 207, 21);
        frame.getContentPane().add(start);
        
        lblNewLabel.setBounds(49, 44, 330, 25);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("开始行");
        lblNewLabel_1.setBounds(49, 77, 54, 21);
        frame.getContentPane().add(lblNewLabel_1);
        
        end.setBounds(109, 108, 207, 21);
        frame.getContentPane().add(end);
        
        JLabel label = new JLabel("\u53D6\u7684\u6761\u6570");
        label.setBounds(49, 108, 54, 21);
        frame.getContentPane().add(label);
        
        code.setBounds(109, 139, 207, 21);
        frame.getContentPane().add(code);
        
        JLabel label_1 = new JLabel("目录编号");
        label_1.setBounds(49, 139, 54, 21);
        frame.getContentPane().add(label_1);
        
        infolab.setBounds(49, 10, 330, 25);
        frame.getContentPane().add(infolab);
    }
    
    class GoThread extends Thread
    {
        public void run()
        {
            DataService dataService = (DataService)ctx.getBean("dataService");
            try
            {
                System.out.println();
                Map<String, String> params = new HashMap<String, String>();
                String st =
                    StringUtils.isEmpty(start.getText()) ? "0"
                        : start.getText();
                String size =
                    StringUtils.isEmpty(end.getText()) ? "0" : end.getText();
                int count = Integer.parseInt(size);
                int stc = Integer.parseInt(st);
                params.put("code", code.getText());
                int c = 1;
                while (count > c)
                {
                    dataService.saveData(params, lblNewLabel, infolab);
                    c++;
                }
                
            }
            catch (Exception e)
            {
                log.error("保存数据失败！", e);
                e.printStackTrace();
                lblNewLabel.setText("插入错误：" + e.getMessage());
            }
            btnNewButton.setEnabled(true);
        }
    }
}
